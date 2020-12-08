package br.com.rac.domain.phrase.model

data class Phrase(
    val at_created: String,
    val disliked: String,
    val likes: String,
    val owner: String,
    val phrase: String
)