package racingcar;

import java.util.*;
import java.util.stream.Collectors;

public class InputView {
    private final int MAX_NAME_LENGTH = 5;
    private final int MIN_NAME_LENGTH = 1;
    private final String ERROR_NOT_INTEGER = "[ERROR] 시도 횟수는 숫자여야 한다.";
    private final String ERROR_NOT_POSITIVE_INTEGER = "[ERROR] 시도 횟수는 양수여야 한다.";
    private final String ERROR_BLANK_INPUT = "[ERROR] 입력값이 공백이면 안된다.";
    private final String ERROR_ZERO_PLAYER = "[ERROR] 참가자 수는 한명 이상이여야 한다.";
    private final String ERROR_MIN_NAME_LENGTH = "[ERROR] 이름이 길이가 " + MIN_NAME_LENGTH + "이상이여야 한다.";
    private final String ERROR_MAX_NAME_LENGTH = "[ERROR] 이름이 길이가 " + MAX_NAME_LENGTH + "이하여야 한다.";
    private final String ERROR_DUPLICATE_NAME = "[ERROR] 이름이 같은 참가자가 있으면 안된다.";
    private final Scanner scanner;

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> getCarNames(){
        List<String> names = getCarNames(scanner.nextLine());
        validateZeroPlayer(names);
        validateBlankName(names);
        validateNameLength(names);
        validateDuplicate(names);
        return names;
    }

    private List<String> getCarNames(String inputString) {
        validateBlankInput(inputString);
        return Arrays.stream(inputString.split(","))
                     .map(String::strip)
                     .collect(Collectors.toList());
    }

    public int getRunCount() {
        int number= getRunCount(scanner.nextLine());
        validatePositiveInt(number);
        return number;
    }

    private int getRunCount(String inputString) {
        try {
            return Integer.parseInt(inputString.strip());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ERROR_NOT_INTEGER);
        }
    }

    private void validateBlankInput(String inputString) {
        if (inputString.isBlank()) {
            throw new IllegalArgumentException(ERROR_BLANK_INPUT);
        }
    }

    private void validateZeroPlayer(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException(ERROR_ZERO_PLAYER);
        }
    }

    private void validateNameLength(List<String> names) {
        names.forEach(this::validateNameLength);
    }

    private void validateNameLength(String name) {
        if (name.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException(ERROR_MIN_NAME_LENGTH);
        }
        if (name.length() > MAX_NAME_LENGTH){
            throw new IllegalArgumentException(ERROR_MAX_NAME_LENGTH);
        }
    }

    private void validateDuplicate(List<String> names) {
        Set<String> set = new HashSet<>();
        names.forEach(name -> {
            if (set.contains(name)) {
                throw new IllegalArgumentException(ERROR_DUPLICATE_NAME);
            }
            set.add(name);
        });
    }

    private void validatePositiveInt(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(ERROR_NOT_POSITIVE_INTEGER);
        }
    }
}
