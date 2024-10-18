
from django.db import models
from accounts.models import CustomUser

class Offering(models.Model):
    title = models.CharField(max_length=200)
    description = models.TextField()
    created_by = models.ForeignKey(CustomUser, on_delete=models.CASCADE, related_name='created_offerings')

class Lesson(models.Model):
    offering = models.OneToOneField(Offering, on_delete=models.CASCADE)
    instructor = models.ForeignKey(CustomUser, on_delete=models.CASCADE, limit_choices_to={'user_type': 'instructor'})
    is_public = models.BooleanField(default=False)

    def save(self, *args, **kwargs):
        if not self.pk:
            self.is_public = True
        super().save(*args, **kwargs)
