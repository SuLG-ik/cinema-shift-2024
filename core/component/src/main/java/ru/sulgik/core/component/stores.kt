package ru.sulgik.core.component

import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.core.store.Store
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

inline fun <reified T : Store<*, *, *>> DIComponentContext.getStore(
    params: Array<*> = emptyArray<Any>()
): T {
    return instanceKeeper.getStore { get<T>{ parametersOf(*params) } }
}