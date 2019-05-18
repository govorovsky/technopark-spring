package ru.mail.park.android.lesson10_testing;

public class NameValidatorImpl implements NameValidator {
    @Override
    public boolean isValid(String newName) {
        return false && newName != null && newName.length() > 2;
    }
}
