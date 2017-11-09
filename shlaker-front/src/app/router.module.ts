import { MainComponent } from './components/main/main.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes }  from '@angular/router';

const mainRoute: Routes = [
    {
        path: '',
        component: MainComponent
    }
];

@NgModule({
    imports: [RouterModule.forRoot(mainRoute)],
    exports: [RouterModule]
})
export class MainAppRout {}
