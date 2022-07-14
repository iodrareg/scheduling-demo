package io.drareg.schedulingdbdemo.components

import com.github.kagkarlsson.scheduler.task.ExecutionComplete
import com.github.kagkarlsson.scheduler.task.ExecutionOperations
import com.github.kagkarlsson.scheduler.task.FailureHandler
import io.drareg.schedulingdbdemo.model.TaskData
import java.time.Instant

class RandomJobFailureHandler : FailureHandler<TaskData> {
    override fun onFailure(p0: ExecutionComplete?, p1: ExecutionOperations<TaskData>?) {
        p1?.reschedule(p0, Instant.now().plusSeconds(5))
    }
}
