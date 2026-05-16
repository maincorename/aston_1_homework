void main(String[] args) {
    CustomHashMap hashMap = new CustomHashMap();

    hashMap.put(1, "число - строка");
    hashMap.put(-1, "отрицательное число - строка");
    hashMap.put(999, 12);
    hashMap.put("key", "строка - строка");

    System.out.println(hashMap.get(1));
    System.out.println(hashMap.get(-1));
    System.out.println(hashMap.get(999));
    System.out.println(hashMap.get("key"));
    System.out.println(hashMap.get("null"));

    System.out.println("Удалённое значение: " + hashMap.remove(-1));

    for (int i = 0; i < 30; i++) {
        hashMap.put(i, i+1);
        System.out.println(hashMap.get(i));
    }
}
