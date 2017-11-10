import { Task } from './models/task.model';
import { Project } from './models/project.model';
import { DESELECT_PROJECT, LOAD_PROJECTS, LOAD_TASKS, SELECT_PROJECT } from './actions';
import { IAppState } from './app.store';

export interface IAppState {
    projects: Project[];
    tasks: Task[];
    selectedProject: Project;
    selectedTask: Task;
}

export const INITIAL_STATE: IAppState = {
    projects: [],
    tasks: [],
    selectedTask: null,
    selectedProject: null
};

export function projectReducer(state = [], action) {
    switch(action.type) {
        case LOAD_PROJECTS: return action.projects
    }
    return state;
}

export function taskReducer(state = [], action) {
    return state;
}

export function selectedTaskReducer(state = null, action) {
    return state;
}

export function selectedProjectReducer(state = null, action) {
    switch(action.type) {
        case SELECT_PROJECT: return action.project
        case DESELECT_PROJECT: return null
    }
    return state;
}
