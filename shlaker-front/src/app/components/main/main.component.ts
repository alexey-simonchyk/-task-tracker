import { Component, OnInit } from '@angular/core';
import { select } from 'ng2-redux';
import { User } from '../../models/user.model';
import { Task } from "../../models/task.model";

@Component({
    selector: 'main-component',
    templateUrl: './main.component.html',
    styleUrls: ['./main.component.css'],
})
export class MainComponent implements OnInit {

    @select('developers') developers: User[];
    @select('tasks') tasks: Task[];

    constructor() { }

    ngOnInit() {
    }

}
