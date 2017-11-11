import { NgRedux, select } from 'ng2-redux';
import { Component, OnInit } from '@angular/core';
import { TaskService } from '../../services/task-service';
import { IAppState } from "../../app.store";
import { ActivatedRoute } from '@angular/router';
import { Task } from '../../models/task.model';

@Component({
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.css']
})
export class TaskComponent implements OnInit {

    @select('selectedTask') selectedTask: Task;

    constructor(
        private ngRedux: NgRedux<IAppState>,
        private taskService: TaskService,
        private route: ActivatedRoute
    ) { }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let taskId = params['taskId'];
            this.taskService.getTask(taskId);
        })
    }

}
