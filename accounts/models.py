
from django.contrib.auth.models import AbstractUser
from django.db import models

class CustomUser(AbstractUser):
    USER_TYPE_CHOICES = (
        ('instructor', 'Instructor'),
        ('client', 'Client'),
        ('guardian', 'Guardian'),
    )
    user_type = models.CharField(max_length=50, choices=USER_TYPE_CHOICES)
    groups = models.ManyToManyField(
        'auth.Group',
        related_name='customuser_set',  # Use a unique related name
        blank=True,
        help_text='The groups this user belongs to.',
        verbose_name='groups',
    )
    user_permissions = models.ManyToManyField(
        'auth.Permission',
        related_name='customuser_set',  # Use a unique related name
        blank=True,
        help_text='Specific permissions for this user.',
        verbose_name='user permissions',
    )