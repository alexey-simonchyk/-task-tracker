import { NgModule } from '@angular/core';
import { ImagePipe } from './image-pipe';
import { DefaultImagePipe } from './defult-image-pipe';

@NgModule({
    declarations: [
        ImagePipe,
        DefaultImagePipe
    ],
    exports: [
        ImagePipe,
        DefaultImagePipe
    ]
})
export class PipeModule {

}
