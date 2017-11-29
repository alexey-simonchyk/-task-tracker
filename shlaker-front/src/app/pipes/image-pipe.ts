import { PipeTransform, Pipe } from '@angular/core';
import { environment } from '../../environments/environment';


@Pipe({ name : 'imageLink'})
export class ImagePipe implements PipeTransform {

    private imageEndPoint = `${environment.defaultImageEndPoint}`;

    transform(value: string, ...args: any[]) {
        if (value) {
            return `${this.imageEndPoint}/${value}`;
        } else {
            return `${this.imageEndPoint}/default/default_user`;
        }
    }


}
