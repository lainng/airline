function setEnglish() {
    localStorage.setItem("tableLang", "https://cdn.datatables.net/plug-ins/1.11.4/i18n/en-gb.json");
}

function setRussian() {
    localStorage.setItem("tableLang", "https://cdn.datatables.net/plug-ins/1.11.4/i18n/ru.json");
}

if (localStorage.getItem("tableLang") === null) {
    localStorage.setItem("tableLang", "https://cdn.datatables.net/plug-ins/1.11.4/i18n/ru.json");
}