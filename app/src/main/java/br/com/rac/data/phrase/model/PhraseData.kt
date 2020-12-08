package br.com.rac.data.phrase.model

import br.com.rac.domain.phrase.model.Phrase

data class PhraseData(
    val at_created: String = "",
    val disliked: String = "",
    val likes: String = "",
    val owner: String = "",
    val phrase: String = ""
)


fun PhraseData.toPhraseMapper(): Phrase = Phrase(
    at_created = this.at_created,
    disliked = this.disliked,
    likes = this.likes,
    owner = this.owner,
    phrase = this.phrase
)