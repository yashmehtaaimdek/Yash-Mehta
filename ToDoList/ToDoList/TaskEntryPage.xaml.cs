using System;
using System.IO;
using Xamarin.Forms;
using ToDoList.Models;
namespace ToDoList
{
    public partial class TaskEntryPage : ContentPage
    {
        public TaskEntryPage()
        {
            InitializeComponent();
        }

        async void Button_Clicked(System.Object sender, System.EventArgs e)
        {
            var task = (Task)BindingContext;
            task.Date = DateTime.UtcNow;
            await App.Database.SaveNoteAsync(task);
            await Navigation.PopAsync();
        }

        async void Button_Clicked_1(System.Object sender, System.EventArgs e)
        {
            var task = (Task)BindingContext;
            await App.Database.DeleteNoteAsync(task);
            await Navigation.PopAsync();
        }
    }
}
