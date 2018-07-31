insert into Admin (enabled, CPF, name, phone) values (1, '50973150815', 'Welson de Lima Teles', '61981346998');
insert into ApplicationUser (enabled, email, password, username, admin_id, doctor_id, employee_id, patient_id) values (1, 'welsonlimawlsn@gmail.com', 'A44AE39DC21A82989D03E6B2BFCA068554531654BDB47EB0869300EF8E091241', 'admin', 1, null, null, null);
insert into ActivateAccount (enabled, activated, expiration, uniqueKey, applicationUser_id) values (1, 1, '2018-08-01 11:28:16', '9C0405BA22FD71F10864903711081C543ADA47D00518114CEF72890E40B47C3A', 1);
