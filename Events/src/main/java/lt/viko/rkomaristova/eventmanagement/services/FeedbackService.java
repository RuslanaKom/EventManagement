package lt.viko.rkomaristova.eventmanagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.viko.rkomaristova.eventmanagement.dao.FeedbackDao;
import lt.viko.rkomaristova.eventmanagement.entities.Event;
import lt.viko.rkomaristova.eventmanagement.entities.Feedback;
import lt.viko.rkomaristova.eventmanagement.entities.User;
@Service
public class FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;
	
	@Transactional
	public void saveFeedback(User user, Event event, String feedbackString) {
		Feedback feedback = new Feedback();
		feedback.setEvent(event);
		feedback.setUser(user);
		feedback.setFeedback(feedbackString);
		feedbackDao.saveFeedback(feedback);
	}
	
	public List<Feedback> getEventFeedbacks(Long eventId){
		return feedbackDao.getFeedBackForEvent(eventId);
	}
}
