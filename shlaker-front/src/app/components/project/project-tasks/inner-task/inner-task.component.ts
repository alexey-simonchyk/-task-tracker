import { environment } from '../../../../../environments/environment';
import { Component, Input, OnInit } from '@angular/core';
import { Task } from '../../../../models/task.model';

@Component({
  selector: 'inner-task',
  templateUrl: './inner-task.component.html',
  styleUrls: ['./inner-task.component.css'],
})
export class InnerTaskComponent implements OnInit {


    @Input('task') task: Task;
    imageEndPoint: string = `${environment.defaultImageEndPoint}`;
    protected isOpenedModalWindow: boolean = false;

    constructor() { }

    ngOnInit() {
    }

    protected openAddDeveloperModal() {
        this.isOpenedModalWindow = true;
    }

    protected onCloseAddDeveloperModal(event) {
        console.log(event);
        this.isOpenedModalWindow = false;
    }

}
