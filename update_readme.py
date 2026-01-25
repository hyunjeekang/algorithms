import os
import requests
import urllib.parse

DIRS = {
    "baekjoon": "baekjoon",
    "swea": "swea"
}
README_PATH = "README.md"
BOJ_API_URL = "https://solved.ac/api/v3/problem/lookup"


def get_boj_problems():
    problem_ids = []
    if os.path.exists(DIRS["baekjoon"]):
        for filename in os.listdir(DIRS["baekjoon"]):
            if filename.endswith(".py"):
                pid = filename.split('.')[0]
                if pid.isdigit():
                    problem_ids.append(pid)
    return sorted(list(set(problem_ids)), key=int)

def fetch_boj_details(ids):
    """solved.ac API"""
    if not ids:
        return {}
    
    id_str = ",".join(ids)
    params = {"problemIds": id_str}
    
    headers = {
        "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36",
        "Accept": "application/json"
    }

    try:
        response = requests.get(BOJ_API_URL, params=params, headers=headers)
        if response.status_code == 200:
            problems = response.json()
            return {str(p['problemId']): p for p in problems}
        else:
            print(f"API Error: {response.status_code}")
    except Exception as e:
        print(f"Error fetching BOJ data: {e}")
    return {}

def get_swea_problems():
    """swea 폴더 파일 파싱"""
    problems = []
    if os.path.exists(DIRS["swea"]):
        for filename in os.listdir(DIRS["swea"]):
            if filename.endswith(".py"):
                parts = filename.replace(".py", "").split("_")
                if len(parts) >= 3:
                    level = parts[0]
                    number = parts[1]
                    title = " ".join(parts[2:])
                    file_path = f"{DIRS['swea']}/{filename}"
                    encoded_path = urllib.parse.quote(file_path)
                    
                    problems.append({
                        "level": level,
                        "number": number,
                        "title": title,
                        "path": encoded_path
                    })
    problems.sort(key=lambda x: (x['level'], x['number']))
    return problems

def generate_markdown(boj_ids, boj_details, swea_data):
    content = "# algorithms 🧌\n\n"

    # BOJ
    content += "## BOJ\n"
    content += "| # | Problem | Solution | Level |\n"
    content += "| :---: | :--- | :---: | :---: |\n"

    for pid in boj_ids:
        info = boj_details.get(pid)
        file_path = f"{DIRS['baekjoon']}/{pid}.py"
        
        if info:
            title = info['titleKo']
            level = info['level']
            tier_img = f"https://static.solved.ac/tier_small/{level}.svg"
            link_boj = f"https://www.acmicpc.net/problem/{pid}"
            
            row = f"| {pid} | [{title}]({link_boj}) | [Python]({file_path}) | <img src='{tier_img}' height='20px'/> |"
        else:
            row = f"| {pid} | 확인 불가 | [Python]({file_path}) | - |"
        
        content += row + "\n"

    # SWEA
    if swea_data:
        content += "\n## SWEA\n"
        content += "| # | Problem | Solution | Level |\n"
        content += "| :---: | :--- | :---: | :---: |\n"

        for p in swea_data:
            row = f"| {p['number']} | {p['title']} | [Python]({p['path']}) | {p['level']} |"
            content += row + "\n"

    return content

def update_readme():
    boj_ids = get_boj_problems()
    boj_details = fetch_boj_details(boj_ids)
    swea_data = get_swea_problems()
    
    markdown = generate_markdown(boj_ids, boj_details, swea_data)
    
    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(markdown)
    print("README.md updated successfully.")

if __name__ == "__main__":
    update_readme()