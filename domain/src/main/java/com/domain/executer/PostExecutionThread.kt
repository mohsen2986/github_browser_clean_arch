package com.domain.executer

import io.reactivex.Scheduler

interface PostExecutionThread{
    val scheduler: Scheduler
}

