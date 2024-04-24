function validateInput(input) {
    if (!/^\d{4}-\d{2}-\d{2}$/.test(input)) {
        return false;
    }

    const parts = input.split('-');

    const year = parseInt(parts[0], 10);
    if (year < 1900 || year > 2024) {
        return false;
    }

    const month = parseInt(parts[1], 10);
    if (month < 1 || month > 12) {
        return false;
    }

    const day = parseInt(parts[2], 10);
    if (day < 1 || day > 31) {
        return false;
    }

    const date = new Date(year, month - 1, day);

    if (date.getFullYear() !== year || date.getMonth() + 1 !== month || date.getDate() !== day) {
        return false;
    }

    return true;
}

export default validateInput;