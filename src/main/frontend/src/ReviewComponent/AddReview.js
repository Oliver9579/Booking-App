import "./AddReview.css"
import {useState} from "react";
import axios from "axios";

const AddReview = ({bookedEntityID, reviewedEntityType, onNewReview}) => {

    const [comment, setComment] = useState('');

    const handleAddNewReview = () => {
        const requestBody = {
            comment: comment,
        };

        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            };
            const url = 'http://localhost:3000/api/reviews/' + reviewedEntityType + bookedEntityID.id;
            axios.post(
                url,
                requestBody,
                config
            ).then((response) => {
                setComment('');
                onNewReview(response.data);
            });
        } catch (error) {
            alert('Error while add review:');
        }
    };
    const handleInputChange = (event) => {
        setComment(event.target.value);
    };

    return (
        <div className="add-review-card blur-include">
            <div className="row">
                <div className="col-10" style={{paddingRight: 0}}>
                    <input className="comment" type="text" name="comment" placeholder="Add review..." value={comment}
                           onChange={handleInputChange}></input>
                </div>
                <div className="col-2" style={{paddingLeft: 0, textAlign: "right"}}>
                    <input className="btn btn-primary add-review-button" type="submit" value="Submit"
                           disabled={comment.trim() === ''} onClick={handleAddNewReview}></input>
                </div>
            </div>
        </div>
    )
}
export default AddReview