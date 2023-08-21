Feature: Booking a Slot

  Scenario: Book a slot successfully
    Given I have opened the booking website

    And the following bookings exist:
      | date       | start_time |
      | 2022-12-10 | 09:00:00   |

    And the following schedule is available:
      | id | date       | start_time | end_time   | price   |
      | 1  | 2022-12-10 | 07:00:00   | 09:00:00   | 800000  |
      | 2  | 2022-12-10 | 09:00:00   | 11:00:00   | 1000000 |
      | 3  | 2022-12-10 | 11:00:00   | 13:00:00   | 1200000 |

    When I try to book a slot
    Then I should receive a confirmation

  Scenario: Attempt to double book
    Given I have opened the booking website

    And the following bookings exist:
      | date       | start_time |
      | 2022-12-10 | 09:00:00   |

    And the following schedule is available:
      | id | date       | start_time | end_time   | price   |
      | 1  | 2022-12-10 | 07:00:00   | 09:00:00   | 800000  |
      | 2  | 2022-12-10 | 09:00:00   | 11:00:00   | 1000000 |
      | 3  | 2022-12-10 | 11:00:00   | 13:00:00   | 1200000 |

    When I try to book a slot
    Then I should not receive a confirmation