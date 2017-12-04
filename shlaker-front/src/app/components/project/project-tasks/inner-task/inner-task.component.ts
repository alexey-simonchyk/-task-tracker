import { Component, Input, OnInit, EventEmitter, Output } from '@angular/core';
import { Task } from '../../../../models/task.model';
import { TaskService } from '../../../../services/task-service';

@Component({
  selector: 'inner-task',
  templateUrl: './inner-task.component.html',
  styleUrls: ['./inner-task.component.css'],
})
export class InnerTaskComponent implements OnInit {

    @Input('task') task: Task;
    protected isOpenedModalWindow: boolean = false;

    constructor(private taskService: TaskService) { }

    ngOnInit() {
    }

    protected openAddDeveloperModal() {
        this.isOpenedModalWindow = true;
    }

    protected onCloseAddDeveloperModal(developers) {
        if (developers) {
            this.taskService.updateTaskDevelopers(developers, this.task.id);
        }
        this.isOpenedModalWindow = false;
    }

}
