import os
import requests
import urllib.parse
import re

DIRS = {
    "baekjoon": "baekjoon",
    "swea": "swea"
}
README_PATH = "README.md"
BOJ_API_URL = "https://solved.ac/api/v3/problem/lookup"
SOLVED_AC_HANDLE = "hyunni3"

# 확장자별 표시 텍스트 설정
EXT_MAP = {
    ".py": "Python",
    ".java": "Java"
}

def get_boj_problems():
    """백준 폴더 내의 문제 번호와 실제 파일명, 언어를 수집합니다."""
    # { '1000': { '.py': '1000.py', '.java': 'BOJ1000.java' } } 형태
    problem_map = {}
    if os.path.exists(DIRS["baekjoon"]):
        for filename in os.listdir(DIRS["baekjoon"]):
            name, ext = os.path.splitext(filename)
            if ext in EXT_MAP:
                # 정규식을 사용해 파일명에서 숫자만 추출 (예: BOJ10026 -> 10026)
                pid = re.sub(r'[^0-9]', '', name)
                
                if pid: # 숫자가 존재하는 경우에만 처리
                    if pid not in problem_map:
                        problem_map[pid] = {}
                    problem_map[pid][ext] = filename # 실제 파일명을 저장
    
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
    problem_map = {}
    
    if os.path.exists(DIRS["swea"]):
        for filename in os.listdir(DIRS["swea"]):
            name, ext = os.path.splitext(filename)
            if ext in EXT_MAP:
                parts = name.split("_")
                # 레벨과 문제번호만 있어도 파싱 가능하도록 (최소 2개 파트)
                if len(parts) >= 2:
                    level = parts[0]
                    number = parts[1]
                    title = "_".join(parts[2:]) if len(parts) > 2 else ""
                    key = (level, number, title)
                    if key not in problem_map:
                        problem_map[key] = {}
                    problem_map[key][ext] = filename # 실제 파일명을 저장
    
    problems = []
    for (level, number, title), ext_dict in problem_map.items():
        problems.append({
            "level": level,
            "number": number,
            "title": title,
            "files": ext_dict # { '.py': '...', '.java': '...' }
        })
    
    problems.sort(key=lambda x: (x['level'], x['number']))
    return problems

def generate_markdown(boj_ids, boj_map, boj_details, swea_data):
    content = "# algorithms \n\n"
    content += f"[![Solved.ac프로필](http://mazassumnida.wtf/api/v2/generate_badge?boj={SOLVED_AC_HANDLE})](https://solved.ac/{SOLVED_AC_HANDLE})\n\n"

    # --- BOJ Section ---
    content += "## BOJ\n"
    content += "| # | Problem | Solution | Level |\n"
    content += "| :---: | :--- | :---: | :---: |\n"

    for pid in boj_ids:
        info = boj_details.get(pid)
        solution_links = []
        
        # 확장자 이름순 정렬 (.java, .py 순)
        for ext in sorted(boj_map[pid].keys()):
            actual_filename = boj_map[pid][ext]
            file_path = f"{DIRS['baekjoon']}/{actual_filename}"
            encoded_path = urllib.parse.quote(file_path)
            solution_links.append(f"[{EXT_MAP[ext]}]({encoded_path})")
        
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
            
            for ext in sorted(p['files'].keys()):
                actual_filename = p['files'][ext]
                file_path = f"{DIRS['swea']}/{actual_filename}"
                encoded_path = urllib.parse.quote(file_path)
                solution_links.append(f"[{EXT_MAP[ext]}]({encoded_path})")
            
            solutions = ", ".join(solution_links)
            display_title = p['title'].replace("_", " ") if p['title'] else "-"
            row = f"| {p['number']} | {display_title} | {solutions} | {p['level']} |"
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