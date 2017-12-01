import { Component, Input, OnInit } from '@angular/core';
import { Task } from "../../../models/task.model";
import * as $ from 'jquery';
import { TaskService } from '../../../services/task-service';

@Component({
    selector: 'project-tasks',
    templateUrl: './project-tasks.component.html',
    styleUrls: ['./project-tasks.component.css']
})
export class ProjectTasksComponent implements OnInit {

    @Input("tasks") tasks: Task[];
    @Input("projectId") projectId: string;

    constructor(private taskService: TaskService) {
    }

    ngOnInit() {

    }

    getTasks(status) {
        return this.tasks ? this.tasks.filter(t => t.status == status) : [];
    }

    onDrop(event, status) {
        event.preventDefault();
        $(event.target).removeClass('drop_here');
        let data = event.dataTransfer.getData('task_id');
        if (this.tasks.find(t => t.id == data).status !== status) {
            this.taskService.updateTaskStatus(data, status);
        }
    }

    onDragStart(event, task) {
        event.dataTransfer.setData("task_id", task.id);
    }

    allowDrag(event) {
        event.preventDefault();
    }

    enterDropArea(target) {
        $(target).addClass('drop_here');
    }

    leaveDropArea(target) {
        $(target).removeClass('drop_here');
    }

}
