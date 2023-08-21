Feature: Booking Validation

  Scenario: Validate booking prices and detect double booking
    Given I have opened the booking website

    And the following bookings exist:
      | Booking_id | venue_id | User_id | date       | Start_time | end_time  | price   |
      | BK/000001  | 15       | 12      | 2022-12-10 | 09:00:00   | 11:00:00  | 1200000 |
      | BK/000005  | 15       | 12      | 2022-12-10 | 09:00:00   | 11:00:00  | 1000000 |

    And the following schedule is available:
      | venue_id | date       | start_time | end_time   | price   |
      | 15       | 2022-12-10 | 07:00:00   | 09:00:00   | 800000  |
      | 15       | 2022-12-10 | 09:00:00   | 11:00:00   | 1000000 |
      | 15       | 2022-12-10 | 11:00:00   | 13:00:00   | 1200000 |

    When the bookings are validated
    Then the following booking issues are detected:
      | Booking_id |
      | BK/000001  |
      | BK/000005  |