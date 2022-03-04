package com.example.proj27_subway.presentation

interface BaseView<PresenterT : BasePresenter>{
    val presenter: PresenterT
}