package lt.viko.rkomaristova.eventmanagement.dao;

import java.util.List;

import lt.viko.rkomaristova.eventmanagement.entities.Feedback;

public interface FeedbackDao {
	
	 void saveFeedback(Feedback feedback);
	 List<Feedback> getFeedBackForEvent(Long eventId);

}
