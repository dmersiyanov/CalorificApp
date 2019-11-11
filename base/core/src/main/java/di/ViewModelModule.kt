package di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.calorific.main.MainScreenViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(com.calorific.main.MainScreenViewModel::class)
    internal abstract fun mainScreenViewModel(viewModel: com.calorific.main.MainScreenViewModel): ViewModel
}