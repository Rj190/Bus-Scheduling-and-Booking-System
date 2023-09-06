import React, { useState } from 'react';



function AddBus({ onAddBus }) {



    const [bus, setBus] = useState({



        BusID: 1, // Initialize BusID to 1



        BusType: '',



        Capacity: '',



        RegistrationNumber: '',



        AvailabilityStatus: '',



    });



    const handleInputChange = (event) => {



        const { name, value } = event.target;



        setBus({



            ...bus,



            [name]: value,



        });



    };



    const handleSubmit = (event) => {



        event.preventDefault();



        if (!bus.BusType || !bus.Capacity || !bus.RegistrationNumber || !bus.AvailabilityStatus) {



            alert('All fields are required.');



            return;



        }



        // Pass the new bus object to the parent component



        onAddBus(bus);



        // Increment BusID for the next bus



        setBus((prevBus) => ({



            ...prevBus,



            BusID: prevBus.BusID + 1,



        }));



        // Clear the input fields



        setBus({



            BusID: bus.BusID + 1, // Increment BusID for the current bus



            BusType: '',



            Capacity: '',



            RegistrationNumber: '',



            AvailabilityStatus: '',



        });



    };



    return (



        <div className="form-container">



            <h2>Add Bus</h2>



            <form onSubmit={handleSubmit}>



                <input



                    type="text"



                    name="BusType"



                    className="form-input"



                    placeholder="Bus Type"



                    value={bus.BusType}



                    onChange={handleInputChange}



                />



                <input



                    type="text"



                    name="Capacity"



                    className="form-input"



                    placeholder="Capacity"



                    value={bus.Capacity}



                    onChange={handleInputChange}



                />



                <input



                    type="text"



                    name="RegistrationNumber"



                    className="form-input"



                    placeholder="Registration Number"



                    value={bus.RegistrationNumber}
                    onChange={handleInputChange}
                />
                <input
                    type="text"
                    name="AvailabilityStatus"
                    className="form-input"
                    placeholder="Availability Status"
                    value={bus.AvailabilityStatus}
                    onChange={handleInputChange}
                />
                <button type="submit" className="form-button">
                    Add Bus
                </button>
            </form>
        </div>
    );
}

export default AddBus;