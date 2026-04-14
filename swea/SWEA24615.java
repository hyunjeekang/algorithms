import java.util.*;

class SWEA24615 {
    class SWEA24615 implements Comparable<Product> {
        int id, originPrice, category, company;
        boolean removed = false;

        public Product(int id, int price, int cat, int com) {
            this.id = id;
            this.originPrice = price;
            this.category = cat;
            this.company = com;
        }

        @Override
        public int compareTo(Product o) {
            // 실제 가격 = originPrice - discounts[category][company]
            // 비교 시점의 가격으로 기격 비교
            int myPrice = this.originPrice - discounts[this.category][this.company];
            int oPrice = o.originPrice - discounts[o.category][o.company];
            
            if (myPrice != oPrice) return Integer.compare(myPrice, oPrice);
            return Integer.compare(this.id, o.id);
        }
    }

    TreeSet<Product>[][] groups;
    int[][] discounts;
    HashMap<Integer, Product> idMap;

    public void init() {
        groups = new TreeSet[6][6];
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                groups[i][j] = new TreeSet<>();
            }
        }
        idMap = new HashMap<>();
        discounts = new int[6][6];
    }

    public int sell(int mID, int mCategory, int mCompany, int mPrice) {
        // 현재 그룹의 누적 할인액 더해서 저장 
        Product p = new Product(mID, mPrice + discounts[mCategory][mCompany], mCategory, mCompany);
        groups[mCategory][mCompany].add(p);
        idMap.put(mID, p);
        return groups[mCategory][mCompany].size();
    }

    public int closeSale(int mID) {
        Product p = idMap.get(mID);
        if (p == null || p.removed) return -1;

        int currentPrice = p.originPrice - discounts[p.category][p.company];
        groups[p.category][p.company].remove(p);
        p.removed = true;
        return currentPrice;
    }

    public int discount(int mCategory, int mCompany, int mAmount) {
        discounts[mCategory][mCompany] += mAmount;
        
        // 음수 가격 된 품목 제거 
        while (!groups[mCategory][mCompany].isEmpty()) {
            Product p = groups[mCategory][mCompany].first();	// peek
            if (p.originPrice - discounts[mCategory][mCompany] <= 0) {
                groups[mCategory][mCompany].pollFirst(); // 제거 
                p.removed = true;
            } else { // 가격이 양수인 경우 
                break; 
            }
        }
        return groups[mCategory][mCompany].size();
    }

    public Solution.RESULT show(int mHow, int mCode) {
        List<Product> candidates = new ArrayList<>();
        
        // mHow -> 탐색 범위
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= 5; j++) {
                boolean target = false;
                if (mHow == 0) target = true;
                else if (mHow == 1 && i == mCode) target = true;
                else if (mHow == 2 && j == mCode) target = true;

                if (target) {
                    int count = 0;
                    for (Product p : groups[i][j]) {
                        candidates.add(p);
                        count++;
                        if (count == 5) break;
                    }
                }
            }
        }

        // 정렬 
        Collections.sort(candidates);

        Solution.RESULT res = new Solution.RESULT();
        res.cnt = Math.min(candidates.size(), 5);
        for (int i = 0; i < res.cnt; i++) {
            res.IDs[i] = candidates.get(i).id;
        }
        return res;
    }
}