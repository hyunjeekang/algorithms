import os
import requests
import urllib.parse

# --- 설정 ---
# 폴더 이름 (실제 리포지토리 폴더명과 일치해야 함)
DIRS = {
    "baekjoon": "baekjoon",
    "swea": "swea"
}
README_PATH = "README.md"
BOJ_API_URL = "https://solved.ac/api/v3/problem/lookup"

# ---------------------------------------------------------

def get_boj_problems():
    """baekjoon 폴더에서 문제 번호 추출"""
    problem_ids = []
    if os.path.exists(DIRS["baekjoon"]):
        for filename in os.listdir(DIRS["baekjoon"]):
            if filename.endswith(".py"):
                pid = filename.split('.')[0]
                if pid.isdigit():
                    problem_ids.append(pid)
    return sorted(list(set(problem_ids)), key=int)

def fetch_boj_details(ids):
    """solved.ac API로 백준 문제 정보 가져오기"""
    if not ids:
        return {}
    
    id_str = ",".join(ids)
    try:
        response = requests.get(f"{BOJ_API_URL}?ids={id_str}")
        if response.status_code == 200:
            problems = response.json()
            return {str(p['problemId']): p for p in problems}
    except Exception as e:
        print(f"Error fetching BOJ data: {e}")
    return {}

def get_swea_problems():
    """swea 폴더에서 '단계_번호_제목.py' 파싱"""
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
    # 난이도 -> 번호 
    problems.sort(key=lambda x: (x['level'], x['number']))
    return problems

def generate_markdown(boj_ids, boj_details, swea_data):
    content = "# algorithms 🧌\n\n"

    # BOJ
    content += "##  Baekjoon Online Judge\n"
    content += "| 티어 | 번호 | 제목 | 풀이 |\n"
    content += "| :---: | :---: | :--- | :---: |\n"

    for pid in boj_ids:
        info = boj_details.get(pid)
        file_path = f"{DIRS['baekjoon']}/{pid}.py"
        
        if info:
            title = info['titleKo']
            level = info['level']
            # solved.ac 티어 이미지 URL
            tier_img = f"https://static.solved.ac/tier_small/{level}.svg"
            link_boj = f"https://www.acmicpc.net/problem/{pid}"
            
            row = f"| <img src='{tier_img}' height='20px'/> | [{pid}]({link_boj}) | {title} | [Python]({file_path}) |"
        else:
            row = f"| - | {pid} | 확인 불가 | [Python]({file_path}) |"
        
        content += row + "\n"

    # SWEA
    if swea_data:
        content += "\n## Samsung SW Expert Academy\n"
        content += "| 난이도 | 번호 | 제목 | 풀이 |\n"
        content += "| :---: | :---: | :--- | :---: |\n"

        for p in swea_data:
            row = f"| {p['level']} | {p['number']} | {p['title']} | [Python]({p['path']}) |"
            content += row + "\n"

    return content

def update_readme():
    # BOJ
    boj_ids = get_boj_problems()
    boj_details = fetch_boj_details(boj_ids)
    
    # SWEA
    swea_data = get_swea_problems()
    
    # md
    markdown = generate_markdown(boj_ids, boj_details, swea_data)
    
    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(markdown)
    print("README.md updated successfully.")

if __name__ == "__main__":
    update_readme()