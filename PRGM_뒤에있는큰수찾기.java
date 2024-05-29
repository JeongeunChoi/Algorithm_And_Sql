package algorithm;

public class PRGM_뒤에있는큰수찾기 {

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        answer[numbers.length - 1] = -1;
        for (int i = numbers.length - 2; i >= 0; i--) {
            int backBiggerNum = -1;
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] < numbers[j]) {
                    backBiggerNum = numbers[j];
                    break;
                } else if (numbers[i] == numbers[j]) {
                    backBiggerNum = answer[j];
                    break;
                } else if (numbers[i] > numbers[j]) {
                    if (answer[j] == -1) {
                        backBiggerNum = -1;
                        break;
                    } else if (answer[j] > numbers[i]) {
                        backBiggerNum = answer[j];
                        break;
                    }
                }
            }
            answer[i] = backBiggerNum;
        }

        return answer;
    }

}
