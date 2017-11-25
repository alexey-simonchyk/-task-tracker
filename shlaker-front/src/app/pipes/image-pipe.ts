import { PipeTransform, Pipe } from '@angular/core';


@Pipe({ name : 'imageLink'})
export class ImagePipe implements PipeTransform {

    transform(value: string, ...args: any[]) {
        if (value) {
            return `/${value}`;
        } else {
            return '/default/default_user';
        }
    }


}
