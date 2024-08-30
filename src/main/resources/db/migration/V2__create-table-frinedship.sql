CREATE TABLE friendship(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    sender_id UUID,
    FOREIGN KEY (sender_id) REFERENCES app_user(id) ON DELETE CASCADE,
    receiver_id UUID,
    FOREIGN KEY (receiver_id) REFERENCES app_user(id) ON DELETE CASCADE,
    is_accepted BOOLEAN NOT NULL,
    accepted_date TIMESTAMP
);