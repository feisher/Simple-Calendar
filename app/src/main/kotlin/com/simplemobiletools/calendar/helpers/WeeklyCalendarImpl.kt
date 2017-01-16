package com.simplemobiletools.calendar.helpers

import android.content.Context
import com.simplemobiletools.calendar.extensions.seconds
import com.simplemobiletools.calendar.interfaces.WeeklyCalendar
import com.simplemobiletools.calendar.models.Event
import org.joda.time.DateTime
import java.util.*

class WeeklyCalendarImpl(val mCallback: WeeklyCalendar, val mContext: Context) : DBHelper.GetEventsListener {
    var mEvents: List<Event>

    lateinit var mTargetDate: DateTime

    init {
        mEvents = ArrayList<Event>()
    }

    fun updateWeeklyCalendar(targetDate: DateTime) {
        mTargetDate = targetDate
        val startTS = mTargetDate.seconds()
        val endTS = mTargetDate.plusWeeks(1).seconds()
        DBHelper(mContext).getEvents(startTS, endTS, this)
    }

    override fun gotEvents(events: MutableList<Event>) {
        mEvents = events
        mCallback.updateWeeklyCalendar(events)
    }
}