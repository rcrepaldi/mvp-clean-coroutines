package br.com.rac.data.network.base


class ResponseBase<T>(val body: T, val header: Map<String, List<String>>)