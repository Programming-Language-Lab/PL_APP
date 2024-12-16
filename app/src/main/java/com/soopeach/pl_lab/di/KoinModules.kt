package com.soopeach.pl_lab.di

import com.soopeach.data.datasource.MemberDataSource
import com.soopeach.data.datasource.MemberDataSourceImpl
import com.soopeach.data.repository.MemberRepositoryImpl
import com.soopeach.domain.repository.MemberRepository
import com.soopeach.presentation.viewmodel.AttendanceScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel<AttendanceScreenViewModel> {
        AttendanceScreenViewModel(
            get()
        )
    }
}

val repositoryModules =
    module {
        single<MemberRepository> { MemberRepositoryImpl(get()) }
    }

val dataSourceModules = module {
    single<MemberDataSource> { MemberDataSourceImpl() }
}

