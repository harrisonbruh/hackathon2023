import cv2
import numpy as np

destination = []


def distance_angle():
    angle = np.arctan2(destination[0], destination[1])
    distance = np.sqrt(destination[0] ** 2 + destination[1] ** 2)
    print(distance, angle)


def track_laser():

    # Camera Dimension Limit
    cam_width = 640
    cam_height = 480

    # Threshold Values
    hue_min = 0
    hue_max = 160
    sat_min = 0
    sat_max = 150
    val_min = 255
    val_max = 255

    lower_red = np.array([hue_min, sat_min, val_min])
    upper_red = np.array([hue_max, sat_max, val_max])

    # Start Video Capture
    cap = cv2.VideoCapture(0)
    cap.set(cv2.CAP_PROP_FRAME_WIDTH, cam_width)
    cap.set(cv2.CAP_PROP_FRAME_HEIGHT, cam_height)

    while True:

        # Take each frame
        res, frame = cap.read()

        # Convert BGR to HSV
        hsv = cv2.cvtColor(frame, cv2.COLOR_RGB2HSV)

        # define range of red color in HSV
        lower_red = np.array([hue_min, sat_min, val_min])
        upper_red = np.array([hue_max, sat_max, val_max])

        # Threshold the HSV image to get only red colors
        mask = cv2.inRange(hsv, lower_red, upper_red)

        # Find contours
        (contours, hierarchy) = cv2.findContours(mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

        dotFound = False

        for contour in contours:
            area = cv2.contourArea(contour)
            if 10 < cv2.contourArea(contour) < 180:
                cv2.drawContours(mask, contour, -1, (0, 255, 0), 1)
                cv2.drawContours(frame, contour, -1, (0, 255, 0), 2)
                dotFound = True
                ((x, y), radius) = cv2.minEnclosingCircle(contour)

                if (1 < radius < 8):
                    cv2.circle(frame, (int(x), int(y)), int(radius), (0, 255, 255), 2)
                    destination = [x,y]
                    print(destination)

        cv2.imshow('frame', frame)

        k = cv2.waitKey(1) & 0xFF
        if k == 27:
            break

    cv2.destroyAllWindows()
    cap.release()


track_laser()
