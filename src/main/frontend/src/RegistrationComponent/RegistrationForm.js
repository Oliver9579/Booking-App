import React, {useState} from 'react';
import './RegistrationForm.css';

const RegistrationForm = () => {
    const [formData, setFormData] = useState({
        firstName: '',
        lastName: '',
        userName: '',
        email: '',
        password: '',
        phoneNumber: ''
    });

    const [registrationSuccess, setRegistrationSuccess] = useState(false);
    const [error, setError] = useState('');

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await fetch('http://localhost:3000/register', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            if (response.ok) {
                setRegistrationSuccess(true);
            } else {
                const data = await response.json();
                setError(data.message);
            }
        } catch (error) {
            setError('Something went wrong.');
        }
    };

    if (registrationSuccess) {
        return (
            <div className="registration-success">
                <p>Registration successful!</p>
                <a href="/login">Go to Login</a>
            </div>
        );
    }

    return (
        <div className="container-fluid vh-100">
            <div className="" style={{marginTop: '200px'}}>
                <div className="rounded d-flex justify-content-center">
                    <div className="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
                        <div className="text-center">
                            <h3 className="text-primary">Create Account</h3>
                        </div>
                        <div className="p-4">
                            <form onSubmit={handleSubmit}>
                                <div className="input-group mb-3">
                                    <input type="text" className="form-control" name="firstName"
                                           placeholder="First Name" value={formData.firstName}
                                           onChange={handleChange} ></input>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="text" className="form-control" name="lastName"
                                           placeholder="Last Name" value={formData.lastName}
                                           onChange={handleChange} ></input>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="text" className="form-control" name="userName"
                                           placeholder="Username" value={formData.userName}
                                           onChange={handleChange} ></input>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="email" className="form-control" name="email"
                                           placeholder="Email" value={formData.email}
                                           onChange={handleChange} ></input>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="password" className="form-control" name="password"
                                           placeholder="Password" value={formData.password}
                                           onChange={handleChange} ></input>
                                </div>
                                <div className="input-group mb-3">
                                    <input type="tel" className="form-control" name="phoneNumber"
                                           placeholder="Phone Number" value={formData.phoneNumber}
                                           onChange={handleChange} ></input>
                                </div>
                                <div className="d-grid mx-auto" style={{textAlign: "center"}}>
                                    <button className="btn btn-primary " type="submit"><span></span> Sign up</button>
                                </div>
                                {error && <p className="error-message text-center mt-3">{error}</p>}
                                <p className="text-center mt-3">Already have an account? <a href="/login">Sign In</a>
                                </p>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegistrationForm;
