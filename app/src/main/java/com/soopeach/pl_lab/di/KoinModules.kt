package com.soopeach.pl_lab.di

import com.soopeach.data.datasource.MemberDataSource
import com.soopeach.data.datasource.MemberDataSourceImpl
import com.soopeach.data.datasource.ProjectDataSource
import com.soopeach.data.datasource.ProjectDataSourceImpl
import com.soopeach.data.repository.MemberRepositoryImpl
import com.soopeach.data.repository.ProjectRepositoryImpl
import com.soopeach.domain.repository.MemberRepository
import com.soopeach.domain.repository.ProjectRepository
import com.soopeach.presentation.viewmodel.AttendanceScreenViewModel
import com.soopeach.presentation.viewmodel.HallOfFameScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModules = module {
    viewModel<AttendanceScreenViewModel> {
        AttendanceScreenViewModel(
            get()
        )
    }
    viewModel<HallOfFameScreenViewModel> {
        HallOfFameScreenViewModel(get())
    }
}

val repositoryModules =
    module {
        single<MemberRepository> { MemberRepositoryImpl(get()) }
        single<ProjectRepository> { ProjectRepositoryImpl(get()) }
    }

val dataSourceModules = module {
    single<MemberDataSource> { MemberDataSourceImpl() }
    single<ProjectDataSource> { ProjectDataSourceImpl() }
}

