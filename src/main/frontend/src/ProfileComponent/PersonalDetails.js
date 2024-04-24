import React, {useState} from 'react';
import "./PersonalDetails.css"
import {useNavigate} from 'react-router-dom';
import validateInput from "./ValidateDateOfBirth";
import {nationalityList} from "./Nationalities";
import axios from "axios";

const PersonalDetails = ({user}) => {

    const [originalUser, setOriginalUser] = useState(user);
    const [editingPart, setEditingPart] = useState('');
    const [form, setForm] = useState({...user});
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleEditClick = (name) => {
        setEditingPart(name);
    };

    const handleSaveClick = () => {
        if (!validateInput(form.dateOfBirth) && editingPart === 'birth') {
            setErrorMessage("Please enter a valid date")
            setForm(originalUser);
        } else if (editingPart === 'nationality' && (form.nationality === "" || form.nationality === undefined)) {
            setErrorMessage("Please select the country/region you're from")
            setForm(originalUser);
        } else if (editingPart === 'gender' && (form.gender === "" || form.gender === undefined)) {
            setErrorMessage("Please select your gender from the list")
            setForm(originalUser);
        } else {
            handleSave()
        }

    };

    const handleCancelClick = () => {
        setErrorMessage('')
        setEditingPart('');
        setForm(originalUser);
    };

    const handleChange = (e) => {
        setForm({...form, [e.target.name]: e.target.value});
    };
    const handleSave = () => {
        const updatedUserData = {...user};
        for (const key in form) {
            if (form.hasOwnProperty(key) && user.hasOwnProperty(key)) {
                updatedUserData[key] = form[key];
            }
        }

        const requestBody = {
            ...updatedUserData
        };

        try {
            const token = localStorage.getItem("token");
            const config = {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            };
            axios.put(
                'http://localhost:3000/api/users',
                requestBody,
                config
            ).then(response => {
                setEditingPart('')
                setOriginalUser(form)
                setErrorMessage('')
            }).catch(error => {
                if (error.response) {
                    setErrorMessage(error.response.data.message);
                    setForm(originalUser);
                }
            });
        } catch (error) {
            alert(error.message);
        }
    };

    const handleDeleteProfile = () => {
        const token = localStorage.getItem("token");
        axios.delete('http://localhost:3000/api/users', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        })
            .then((response) => {
                return (
                    navigate("/")
                )
            })
            .catch((error) => {
                if (error.response) {
                    setErrorMessage(error.response.data.message);
                } else {
                    alert(error.message);
                }
            });
    };

    return (
        <div className="personal-details-card">
            <h1>Personal Details</h1>
            <div style={{fontWeight: 'lighter'}}>Update your information and find out how it's used.</div>
            <hr/>
            <div style={{paddingLeft: '2%'}}>
                {/*name*/}
                <div className="row" style={{paddingTop: '2px', paddingBottom: '2px'}}>
                    <div className="col-3" style={{paddingRight: 0, paddingTop: '6px'}}>Name</div>
                    {editingPart === 'name' ? (
                        <div className="col-9 row">
                            <div className="form-group col-5">
                                <label htmlFor="firstName"><strong style={{fontSize: '14px'}}>First name</strong>
                                    {errorMessage ?
                                        <div style={{color: "red", fontSize: '15px'}}>{errorMessage}</div> : ''}
                                </label>
                                <input className="form-control" type="text"
                                       placeholder={form.firstName === undefined ? user.firstName : form.firstName}
                                       value={form.firstName === undefined ? user.firstName : form.firstName}
                                       name="firstName"
                                       onChange={handleChange}/>
                            </div>
                            <div className="form-group col-5">
                                <label htmlFor="lastName"><strong style={{fontSize: '14px'}}>Last name</strong>
                                    {errorMessage ?
                                        <div></div> : ''}
                                </label>
                                <input className="form-control" type="text"
                                       placeholder={form.lastName === undefined ? user.lastName : form.lastName}
                                       value={form.lastName === undefined ? user.lastName : form.lastName}
                                       name="lastName"
                                       onChange={handleChange}/>
                            </div>
                            <div className="col-2" style={{paddingRight: 0, textAlign: 'center'}}>
                                <button className="btn btn-primary cancel-button" onClick={handleCancelClick}>Cancel
                                </button>
                                <button className="btn btn-primary" style={{marginTop: '50px'}}
                                        disabled={((form.firstName === undefined && form.lastName === undefined) ||
                                                (form.firstName === "" || form.lastName === "")) ||
                                            ((form.firstName === (originalUser.firstName ? originalUser.firstName : user.firstName)) &&
                                                (form.lastName === (originalUser.lastName ? originalUser.lastName : user.lastName)))}
                                        onClick={handleSaveClick}>Save
                                </button>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className="col-7"
                                 style={{
                                     paddingLeft: 0,
                                     fontWeight: '350',
                                     paddingTop: '6px'
                                 }}>{`${originalUser.firstName === undefined ? user.firstName : originalUser.firstName} 
                                 ${originalUser.lastName === undefined ? user.lastName : originalUser.lastName}`}</div>
                            <div className="col-2" style={{textAlign: "right"}}>
                                <button className="btn btn-primary edit-button"
                                        disabled={!(editingPart === '')}
                                        onClick={() => handleEditClick('name')}>Edit
                                </button>
                            </div>
                        </>
                    )}
                </div>
                <hr/>
                {/*email*/}
                <div className="row" style={{paddingTop: '2px', paddingBottom: '2px'}}>
                    <div className="col-3" style={{paddingRight: 0, paddingTop: '6px'}}>Email address</div>
                    {editingPart === 'email' ? (
                        <div className="col-9 row">
                            <div className="form-group col-10">
                                <label htmlFor="email"><strong style={{fontSize: '14px'}}>Email address</strong>
                                    {errorMessage ?
                                        <div style={{color: "red", fontSize: '15px'}}>{errorMessage}</div> : ''}
                                </label>
                                <input className="form-control" type="text"
                                       style={{marginLeft: 0}}
                                       placeholder={form.email === undefined ? user.email : form.email}
                                       value={form.email === undefined ? user.email : form.email}
                                       name="email"
                                       onChange={handleChange}/>
                            </div>
                            <div className="col-2" style={{paddingRight: 0, textAlign: 'center'}}>
                                <button className="btn btn-primary cancel-button" onClick={handleCancelClick}>Cancel
                                </button>
                                <button className="btn btn-primary" style={{marginTop: '50px'}}
                                        disabled={(form.email === undefined
                                                || form.email === "")
                                            || form.email === (originalUser.email ? originalUser.email : user.email)}
                                        onClick={handleSaveClick}>Save
                                </button>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className="col-7"
                                 style={{
                                     paddingLeft: 0,
                                     fontWeight: '350',
                                     paddingTop: '6px'
                                 }}>{originalUser.email === undefined ? user.email : originalUser.email}</div>
                            <div className="col-2" style={{textAlign: "right"}}>
                                <button className="btn btn-primary edit-button"
                                        disabled={!(editingPart === '')}
                                        onClick={() => handleEditClick('email')}>Edit
                                </button>
                            </div>
                        </>
                    )}
                </div>
                <hr/>
                {/*phone*/}
                <div className="row" style={{paddingTop: '2px', paddingBottom: '2px'}}>
                    <div className="col-3" style={{paddingRight: 0, paddingTop: '6px'}}>Phone Number</div>
                    {editingPart === 'phone' ? (
                        <div className="col-9 row">
                            <div className="form-group col-10 number-without-arrow">
                                <label htmlFor="phone"><strong style={{fontSize: '14px'}}>Phone number</strong>
                                    {errorMessage ?
                                        <div style={{color: "red", fontSize: '15px'}}>{errorMessage}</div> : ''}
                                </label>
                                <input className="form-control" type="number"
                                       style={{marginLeft: 0}}
                                       placeholder={form.phoneNumber === undefined ? user.phoneNumber : form.phoneNumber}
                                       value={form.phoneNumber === undefined ? user.phoneNumber : form.phoneNumber}
                                       name="phoneNumber"
                                       onChange={handleChange}/>
                            </div>
                            <div className="col-2" style={{paddingRight: 0, textAlign: 'center'}}>
                                <button className="btn btn-primary cancel-button" onClick={handleCancelClick}>Cancel
                                </button>
                                <button className="btn btn-primary" style={{marginTop: '50px'}}
                                        disabled={form.phoneNumber === undefined
                                            || form.phoneNumber === ""
                                            || form.phoneNumber === (originalUser.phoneNumber ? originalUser.phoneNumber : user.phoneNumber)}
                                        onClick={handleSaveClick}>Save
                                </button>
                            </div>
                        </div>
                    ) : (
                        <>
                            <div className="col-7"
                                 style={{
                                     paddingLeft: 0,
                                     fontWeight: '350',
                                     paddingTop: '6px'
                                 }}>{originalUser.phoneNumber === undefined ? user.phoneNumber : originalUser.phoneNumber}</div>
                            <div className="col-2" style={{textAlign: "right"}}>
                                <button className="btn btn-primary edit-button"
                                        disabled={!(editingPart === '')}
                                        onClick={() => handleEditClick('phone')}>Edit
                                </button>
                            </div>
                        </>
                    )}
                </div>
                <hr/>
                {/*birth*/}
                <div className="row" style={{paddingTop: '2px', paddingBottom: '2px'}}>
                    <div className="col-3" style={{paddingRight: 0, paddingTop: '6px'}}>Date of birth</div>
                    {editingPart === 'birth' ? (
                        <div className="col-9 row">
                            <div className="form-group col-10 number-without-arrow"
                                 style={{paddingLeft: '2px', paddingRight: '2px'}}>
                                <label htmlFor="birth"><strong style={{fontSize: '14px'}}>Date of Birth</strong>
                                    {errorMessage ?
                                        <div style={{color: "red", fontSize: '15px'}}>{errorMessage}</div> : ''}
                                </label>
                                <input className="form-control" type="text"
                                       placeholder="YYYY-mm-DD"
                                       style={{marginLeft: 0}}
                                       value={form.dateOfBirth === undefined ? user.dateOfBirth : form.dateOfBirth}
                                       name="dateOfBirth"
                                       onChange={handleChange}/>
                            </div>
                            <div className="col-2" style={{textAlign: 'center'}}>
                                <button className="btn btn-primary cancel-button" onClick={handleCancelClick}>Cancel
                                </button>
                                <button className="btn btn-primary" style={{marginTop: '50px'}} type="submit"
                                        onClick={handleSaveClick}>Save
                                </button>
                            </div>
                        </div>
                    ) : (
                        <>
                            {user.dateOfBirth ? (
                                <div className="col-7"
                                     style={{
                                         paddingLeft: 0, fontWeight: '350', paddingTop: '6px'
                                     }}>{originalUser.dateOfBirth ? originalUser.dateOfBirth : user.dateOfBirth}
                                </div>
                            ) : (
                                <div className="col-7"
                                     style={{paddingLeft: 0, fontWeight: '350', paddingTop: '6px'}}>
                                    Enter your date of birth
                                </div>
                            )}
                            <div className="col-2" style={{textAlign: "right"}}>
                                <button className="btn btn-primary edit-button"
                                        disabled={!(editingPart === '')}
                                        onClick={() => handleEditClick('birth')}>Edit
                                </button>
                            </div>
                        </>
                    )}
                </div>
                <hr/>
                {/*nationality*/}
                <div className="row" style={{paddingTop: '2px', paddingBottom: '2px'}}>
                    <div className="col-3" style={{paddingRight: 0, paddingTop: '6px'}}>Nationality</div>
                    {editingPart === 'nationality' ? (
                        <div className="col-9 row">
                            <div className="form-group col-10 number-without-arrow"
                                 style={{paddingLeft: '2px', paddingRight: '2px'}}>
                                <label htmlFor="nationality"><strong style={{fontSize: '14px'}}>Nationality</strong>
                                    {errorMessage ?
                                        <div style={{color: "red", fontSize: '15px'}}>{errorMessage}</div> : ''}
                                </label>
                                <br/>
                                <select value={form.nationality} name="nationality"
                                        style={{
                                            width: '400px',
                                            border: '1px solid #ccc',
                                            margin: '0 auto',
                                            padding: '8px',
                                            borderRadius: '5px'
                                        }}
                                        onChange={handleChange}>
                                    <option value="">Select your country/region you're from</option>
                                    {nationalityList.map((nationality, index) => (
                                        <option key={index} value={nationality}>{nationality}</option>
                                    ))}
                                </select>
                            </div>
                            <div className="col-2" style={{textAlign: 'center'}}>
                                <button className="btn btn-primary cancel-button" onClick={handleCancelClick}>Cancel
                                </button>
                                <button className="btn btn-primary" style={{marginTop: '50px'}} type="submit"
                                        onClick={handleSaveClick}>Save
                                </button>
                            </div>
                        </div>
                    ) : (
                        <>
                            {user.nationality ? (
                                <div className="col-7"
                                     style={{
                                         paddingLeft: 0,
                                         fontWeight: '350',
                                         paddingTop: '6px'
                                     }}>{originalUser.nationality ? originalUser.nationality : user.nationality}</div>
                            ) : (
                                <div className="col-7"
                                     style={{paddingLeft: 0, fontWeight: '350', paddingTop: '6px'}}>
                                    Select your country/region you're from</div>
                            )}
                            <div className="col-2" style={{textAlign: "right"}}>
                                <button className="btn btn-primary edit-button"
                                        disabled={!(editingPart === '')}
                                        onClick={() => handleEditClick('nationality')}>Edit
                                </button>
                            </div>
                        </>
                    )}
                </div>
                <hr/>
                {/*gender*/}
                <div className="row" style={{paddingTop: '2px', paddingBottom: '2px'}}>
                    <div className="col-3" style={{paddingRight: 0, paddingTop: '6px'}}>Gender</div>
                    {editingPart === 'gender' ? (
                        <div className="col-9 row">
                            <div className="form-group col-10 number-without-arrow"
                                 style={{paddingLeft: '2px', paddingRight: '2px'}}>
                                <label htmlFor="gender"><strong style={{fontSize: '14px'}}>Gender</strong>
                                    {errorMessage ?
                                        <div style={{color: "red", fontSize: '15px'}}>{errorMessage}</div> : ''}
                                </label>
                                <br/>
                                <select value={form.gender} name="gender"
                                        style={{
                                            width: '400px',
                                            border: '1px solid #ccc',
                                            margin: '0 auto',
                                            padding: '8px',
                                            borderRadius: '5px'
                                        }}
                                        onChange={handleChange}>
                                    <option value="">Select your gender</option>
                                    <option value="Male">I am a man</option>
                                    <option value="Woman">I am a woman</option>
                                    <option value="Unknown">I prefer not to say</option>
                                </select>
                            </div>
                            <div className="col-2" style={{textAlign: 'center'}}>
                                <button className="btn btn-primary cancel-button" onClick={handleCancelClick}>Cancel
                                </button>
                                <button className="btn btn-primary" style={{marginTop: '50px'}} type="submit"
                                        onClick={handleSaveClick}>Save
                                </button>
                            </div>
                        </div>
                    ) : (
                        <>
                            {user.gender ? (
                                <div className="col-7"
                                     style={{
                                         paddingLeft: 0, fontWeight: '350', paddingTop: '6px'
                                     }}>{originalUser.gender ? originalUser.gender : user.gender}</div>
                            ) : (
                                <div className="col-7"
                                     style={{paddingLeft: 0, fontWeight: '350', paddingTop: '6px'}}>
                                    Select your gender</div>
                            )}
                            <div className="col-2" style={{textAlign: "right"}}>
                                <button className="btn btn-primary edit-button"
                                        disabled={!(editingPart === '')}
                                        onClick={() => handleEditClick('gender')}>Edit
                                </button>
                            </div>
                        </>
                    )}
                </div>
                <hr/>
                <div style={{textAlign: "center"}}>
                    <button className="btn btn-danger" style={{width: '40%'}}
                            onClick={handleDeleteProfile}>Delete profile
                    </button>
                </div>
            </div>
        </div>
    )
}

export default PersonalDetails;