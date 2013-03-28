package org.omships.omships;

public class VimeoItem extends FeedItem {
	public VimeoItem(FeedItem item){
		this.setTitle(item.getTitle());
		this.setDescription(item.getDescription());
		this.setPubDate(item.getPubDate());
		this.setLink(item.getLink());
	}
	@Override
	public int getImageResource() {
		return R.drawable.videos;
	}
	@Override
	public String getDescription() {
		return super.getTitle();
	}
	@Override
	public String toString() {
		return this.getDescription();
	}
}//end class VimeoItem
