package com.calorificapp.features.main.model


// Класс YearlyPics содержит 12 объектов MonthlyPics
class YearlyPics(var list: MutableList<MonthlyPics> = mutableListOf(
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics(),
        MonthlyPics()))

// Массив weeklyPics содержит 4 фотографии (по 1 на каждую неделю),
// каждый элемент это локальный путь изображения
class MonthlyPics(var weeklyPics: MutableList<String> = mutableListOf("", "", "", ""))

fun MutableList<String>.addWeeklyPic(index: Int, path: String) {
    if(this.size > 4) {
        throw ArrayIndexOutOfBoundsException("You can add only 4 weekly pics")
    } else {
        this.set(index, path)
    }
}

