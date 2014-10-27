package org.punnoose.hibernate.dao;

import org.punnoose.hibernate.model.RegistrationDetails;
import org.punnoose.hibernate.model.Vehicle;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleDao extends GenericeDao<Vehicle, RegistrationDetails>{
}
