package org.koydi.shlaker.controller;

import org.koydi.shlaker.dto.CommentDto;
import org.koydi.shlaker.entity.Comment;
import org.koydi.shlaker.mapper.CommentMapper;
import org.koydi.shlaker.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@CrossOrigin("http://localhost:4200")
public class CommentController {

    private final CommentService commentService;

    private final CommentMapper commentMapper;

    @Autowired
    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/project/{project_id}")
    public CommentDto addCommentToProject(@PathVariable("project_id") String projectId, @RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.fromCommentDto(commentDto);
        commentService.addCommentToProject(projectId, comment);
        return commentMapper.toCommentDto(comment);
    }

    @PostMapping("/task/{task_id}")
    public CommentDto addCommentToTask(@PathVariable("task_id") String taskId, @RequestBody CommentDto commentDto) {
        Comment comment = commentMapper.fromCommentDto(commentDto);
        commentService.addCommentToTask(taskId, comment);
        return commentMapper.toCommentDto(comment);
    }
}
