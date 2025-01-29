package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
    private String number = "0"; // string containing the binary value '0' or '1'

    /**
     * A constructor that generates a binary object.
     *
     * @param number a String of the binary values. It should contain only zeros or ones with any length and order.
     *               otherwise, the value of "0" will be stored. Trailing zeros will be excluded and empty string
     *               will be considered as zero.
     */
    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0"; // Default to "0" 
            return;
        }

        // Validate the binary string 
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0"; // Default to "0" 
                return;
            }
        }

        // Remove leading zeros
        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        // Iensure number is "0"
        this.number = (beg == number.length()) ? "0" : number.substring(beg);
    }

    /**
     * Return the binary value of the variable
     *
     * @return the binary value in a string format.
     */
    public String getValue() {
        return this.number;
    }

    /**
     * Adding two binary variables
     *
     * @param num1 The first addend object
     * @param num2 The second addend object
     * @return A binary variable with a value 
     */
    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = ""; // the binary value of the sum
        while (ind1 >= 0 || ind2 >= 0 || carry != 0) { // loop through all digits
            int sum = carry; // previous carry
            if (ind1 >= 0) { // if num1 has a digit to add
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
                ind1--; // update 
            }
            if (ind2 >= 0) { // if num2 has a digit to add
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
                ind2--; // update 
            }
            carry = sum / 2; // the new carry
            sum = sum % 2; // the remaining digit
            num3 = ((sum == 0) ? "0" : "1") + num3; // convert sum to string and append it to num3
        }
        return new Binary(num3); // create a binary object with the calculated value.
    }

        /**
     * Perform bitwise OR on two Binary objects.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return Result of OR operation as a Binary object
     */
    public static Binary or(Binary num1, Binary num2) {
        String result = performBitwiseOperation(num1.number, num2.number, (a, b) -> (a == '1' || b == '1') ? '1' : '0');
        return new Binary(result);
    }

    /**
     * Perform bitwise AND on two Binary objects.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return Result of AND operation as a Binary object
     */
    public static Binary and(Binary num1, Binary num2) {
        String result = performBitwiseOperation(num1.number, num2.number, (a, b) -> (a == '1' && b == '1') ? '1' : '0');
        return new Binary(result);
    }

    /**
     * Multiply two Binary objects using repeated addition.
     *
     * @param num1 First binary number
     * @param num2 Second binary number
     * @return Result of Multiply operation as a Binary object
     */
    public static Binary multiply(Binary num1, Binary num2) {
        Binary result = new Binary("0");
        Binary one = new Binary("1");
        Binary counter = new Binary(num2.number);
        while (!counter.getValue().equals("0")) {
            result = add(result, num1);
            counter = subtract(counter, one);
        }
        return result;
    }

    public static Binary subtract(Binary num1, Binary num2) {
        String result = "";
        int borrow = 0;
    
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        String bin1 = String.format("%" + maxLength + "s", num1.number).replace(' ', '0');
        String bin2 = String.format("%" + maxLength + "s", num2.number).replace(' ', '0');
    
        for (int i = maxLength - 1; i >= 0; i--) {
            int bit1 = bin1.charAt(i) - '0';
            int bit2 = bin2.charAt(i) - '0' + borrow;
    
            if (bit1 < bit2) {
                bit1 += 2;
                borrow = 1;
            } else {
                borrow = 0;
            }
    
            result = (bit1 - bit2) + result;
        }
    
        // Remove leading zeros
        result = result.replaceFirst("^0+(?!$)", "");
    
        return new Binary(result.isEmpty() ? "0" : result);
    }

    // Helper method for bitwise operations
    private interface BitwiseOp {
        char apply(char a, char b);
    }

    private static String performBitwiseOperation(String a, String b, BitwiseOp op) {
        int maxLength = Math.max(a.length(), b.length());
        a = String.format("%" + maxLength + "s", a).replace(' ', '0');
        b = String.format("%" + maxLength + "s", b).replace(' ', '0');
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            char bitA = a.charAt(i);
            char bitB = b.charAt(i);
            sb.append(op.apply(bitA, bitB));
        }
        String result = sb.toString().replaceFirst("^0+(?!$)", "");
        return result.isEmpty() ? "0" : result;
    }
}