import React from "react";

const Review = ({reviews, toggleReviewsModal, showReviewsModal}) => {

    return (
        showReviewsModal && (
            <div className="modal show blur-exclude review" tabIndex="-1" role="dialog" style={{display: 'block'}}>
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title">Reviews</h5>
                            <button type="button" className="close" onClick={toggleReviewsModal}>
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            {reviews.length > 0 ? (
                                reviews.map((review, index) => (
                                    <div key={index}>
                                        <div>
                                            <strong>Username:</strong> {review.username} - <strong>Review
                                            Date:</strong> {review.reviewDate}
                                        </div>
                                        <div>
                                            <strong>Comment:</strong> {review.comment}
                                        </div>
                                        <hr/>
                                    </div>
                                ))
                            ) : (
                                <div className="alert alert-danger">
                                    <h6>There are no reviews!</h6>
                                </div>
                            )}
                        < /div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" onClick={toggleReviewsModal}>Close
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        )
    );
};
export default Review;