package ru.avacodo.fktesttask.data.dto

data class FitData(
    val lessons: List<Lesson>,
    val option: Option,
    val tabs: List<Tab>,
    val trainers: List<Trainer>
)