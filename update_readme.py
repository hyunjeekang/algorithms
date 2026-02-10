import os
import requests
import urllib.parse

DIRS = {
    "baekjoon": "baekjoon",
    "swea": "swea"
}
README_PATH = "README.md"
BOJ_API_URL = "https://solved.ac/api/v3/problem/lookup"

# 확장자별 표시 텍스트 설정
EXT_MAP = {
    ".py": "Python",
    ".java": "Java"
}

def get_boj_problems():
    """백준 폴더 내의 문제 번호와 사용된 언어들을 수집합니다."""
    # { '1000': ['.py', '.java'], '1001': ['.py'] } 형태의 딕셔너리 생성
    problem_map = {}
    if os.path.exists(DIRS["baekjoon"]):
        for filename in os.listdir(DIRS["baekjoon"]):
            name, ext = os.path.splitext(filename)
            if ext in EXT_MAP and name.isdigit():
                if name not in problem_map:
                    problem_map[name] = []
                problem_map[name].append(ext)
    
    sorted_ids = sorted(problem_map.keys(), key=int)
    return sorted_ids, problem_map

def fetch_boj_details(ids):
    """solved.ac API 호출"""
    if not ids: return {}
    
    id_str = ",".join(ids)
    params = {"problemIds": id_str}
    headers = {
        "User-Agent": "Mozilla/5.0",
        "Accept": "application/json"
    }

    try:
        response = requests.get(BOJ_API_URL, params=params, headers=headers)
        if response.status_code == 200:
            problems = response.json()
            return {str(p['problemId']): p for p in problems}
    except Exception as e:
        print(f"Error fetching BOJ data: {e}")
    return {}

def get_swea_problems():
    """SWEA 폴더 내 파일들을 파싱하여 정보와 언어를 그룹화합니다."""
    # { ('D3', '5215', '햄버거다이어트'): ['.py', '.java'] }
    problem_map = {}
    
    if os.path.exists(DIRS["swea"]):
        for filename in os.listdir(DIRS["swea"]):
            name, ext = os.path.splitext(filename)
            if ext in EXT_MAP:
                parts = name.split("_")
                if len(parts) >= 3:
                    key = (parts[0], parts[1], " ".join(parts[2:]))
                    if key not in problem_map:
                        problem_map[key] = []
                    problem_map[key].append(ext)
    
    # 리스트로 변환 및 정렬
    problems = []
    for (level, number, title), exts in problem_map.items():
        problems.append({
            "level": level,
            "number": number,
            "title": title,
            "exts": sorted(exts) # .java, .py 순서
        })
    
    problems.sort(key=lambda x: (x['level'], x['number']))
    return problems

def generate_markdown(boj_ids, boj_map, boj_details, swea_data):
    content = "# algorithms 🧌\n\n"

    # --- BOJ Section ---
    content += "## BOJ\n"
    content += "| # | Problem | Solution | Level |\n"
    content += "| :---: | :--- | :---: | :---: |\n"

    for pid in boj_ids:
        info = boj_details.get(pid)
        # 확장자별 링크 생성
        solution_links = []
        for ext in sorted(boj_map[pid]):
            file_path = f"{DIRS['baekjoon']}/{pid}{ext}"
            solution_links.append(f"[{EXT_MAP[ext]}]({file_path})")
        
        solutions = ", ".join(solution_links)
        
        if info:
            title = info['titleKo']
            level = info['level']
            tier_img = f"https://static.solved.ac/tier_small/{level}.svg"
            link_boj = f"https://www.acmicpc.net/problem/{pid}"
            row = f"| {pid} | [{title}]({link_boj}) | {solutions} | <img src='{tier_img}' height='20px'/> |"
        else:
            row = f"| {pid} | 확인 불가 | {solutions} | - |"
        content += row + "\n"

    # --- SWEA Section ---
    if swea_data:
        content += "\n## SWEA\n"
        content += "| # | Problem | Solution | Level |\n"
        content += "| :---: | :--- | :---: | :---: |\n"

        for p in swea_data:
            solution_links = []
            for ext in p['exts']:
                # 파일명 복원 (Level_Number_Title.ext)
                filename = f"{p['level']}_{p['number']}_{p['title'].replace(' ', '_')}{ext}"
                file_path = f"{DIRS['swea']}/{filename}"
                encoded_path = urllib.parse.quote(file_path)
                solution_links.append(f"[{EXT_MAP[ext]}]({encoded_path})")
            
            solutions = ", ".join(solution_links)
            row = f"| {p['number']} | {p['title']} | {solutions} | {p['level']} |"
            content += row + "\n"

    return content

def update_readme():
    boj_ids, boj_map = get_boj_problems()
    boj_details = fetch_boj_details(boj_ids)
    swea_data = get_swea_problems()
    
    markdown = generate_markdown(boj_ids, boj_map, boj_details, swea_data)
    
    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write(markdown)
    print("README.md updated successfully.")

if __name__ == "__main__":
    update_readme()