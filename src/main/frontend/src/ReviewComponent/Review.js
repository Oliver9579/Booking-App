const Review = ({ reviews }) => {
    return (
        <div>
            {reviews.map((review, index) => (
                <div key={index}>
                    <div>
                        <strong>Username:</strong> {review.username} - <strong>Review Date:</strong> {review.reviewDate}
                    </div>
                    <div>
                        <strong>Comment:</strong> {review.comment}
                    </div>
                    <hr/>
                </div>
            ))}
        </div>
    );
};
export default Review;