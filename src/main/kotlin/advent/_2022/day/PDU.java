package advent._2022.day;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class PDU implements Comparable<PDU> {
    private final List<PDU> child = new ArrayList<>();
    private int val = -1;
    private boolean isNum = true;
    private final String rawValue;
    public PDU(String input) {
        this.rawValue = input;
        if (!input.startsWith("[")) {
            val = Integer.parseInt(input);
        } else {
            input = input.substring(1, input.length() - 1);
            int level = 0;
            StringBuilder tmp = new StringBuilder();
            for (char c : input.toCharArray()) {
                if (c == ',' && level == 0) {
                    child.add(new PDU(tmp.toString()));
                    tmp = new StringBuilder();
                } else {
                    level += (c == '[') ? 1 : (c == ']') ? -1 : 0;
                    tmp.append(c);
                }
            }
            if (!tmp.toString().equals("")) {
                child.add(new PDU(tmp.toString()));
            }
            isNum = false;
        }
    }
    @Override
    public int compareTo(PDU other) {
        if (isNum && other.isNum) {
            return Integer.compare(other.val, val);
        }
        if (!isNum && !other.isNum) {
            for (int i = 0; i < Math.min(child.size(), other.child.size()); i++) {
                int val = child.get(i).compareTo(other.child.get(i));
                if (val != 0) {
                    return val;
                }
            }
            return other.child.size() - child.size();
        }
        PDU pdu1 = isNum ? new PDU("[" + val + "]") : this;
        PDU pdu2 = other.isNum ? new PDU("[" + other.val + "]") : other;
        return pdu1.compareTo(pdu2);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PDU that = (PDU) o;
        return rawValue.equals(that.rawValue);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(rawValue);
    }
}