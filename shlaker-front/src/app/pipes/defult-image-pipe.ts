import { Pipe, PipeTransform } from '@angular/core';
import { environment } from '../../environments/environment';

@Pipe({
    name: 'defaultImageLink'
})
export class DefaultImagePipe implements PipeTransform {

    private imageEndPoint: string = `${environment.defaultImageEndPoint}`;

    transform(value: any, ...args: any[]): any {
        if (value) {
            return `${this.imageEndPoint}/default/${value}`;
        }
    }

}
