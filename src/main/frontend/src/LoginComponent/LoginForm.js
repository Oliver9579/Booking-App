import React, {useState} from 'react';
import './LoginForm.css';
import {Navigate} from 'react-router-dom';

const LoginForm = () => {
    const [formData, setFormData] = useState({
        userName: '',
        password: ''
    });

    const [loginSuccess, setLoginSuccess] = useState(false);
    const [error, setError] = useState('');

    const handleChange = (e) => {
        const {name, value} = e.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {

            const response = await fetch('http://localhost:3000/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(formData)
            });
            if (response.ok) {
                setLoginSuccess(true)
                const data = await response.json();
                localStorage.setItem('token', data.token)
            } else {
                const data = await response.json();
                setError(data.message);
            }
        } catch (error) {
            setError('Something went wrong.');
        }
    };

    if (loginSuccess) {
        return <Navigate to="/dashboard" />;
    }

    return (
        <div className="container-fluid vh-100">
            <div className="" style={{marginTop: '200px'}}>
                <div className="rounded d-flex justify-content-center">
                    <div className="col-md-4 col-sm-12 shadow-lg p-5 bg-light">
                        <div className="text-center">
                            <h3 className="text-primary">Log In</h3>
                        </div>
                        <div className="p-4">
                            <form onSubmit={handleSubmit}>
                                <div className="input-group mb-3 justify-content-center">
                                    <input className="form-control" type="text" name="userName" placeholder="Username" value={formData.userName}
                                           onChange={handleChange}/>
                                </div>
                                <div className="input-group mb-3 justify-content-center">
                                    <input className="form-control" type="password" name="password" placeholder="Password"
                                           value={formData.password}
                                           onChange={handleChange}/>
                                </div>
                                <div className="d-grid mx-auto text-center">
                                    <button className="btn btn-primary form-control" type="submit" style={{padding: '0px'}}>Log in</button>
                                </div>
                            </form>
                            {error && <p className="error-message text-center">{error}</p>}
                            <p className="text-center mt-3">Don't have an account? <a href="/">Sign Up</a>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default LoginForm;
